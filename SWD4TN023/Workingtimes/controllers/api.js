const express = require('express')
const jwt = require('jsonwebtoken')

const User = require('../models/user')
const Entry = require('../models/entry')
const apiRoutes = express.Router()
const secret = require('../config').secret


// GET HELLO PAGE
apiRoutes.get('/', function (req, res) {
  res.json({
    message: 'Workingtimes API'
  })
})


// AUTHENTICATE
// Credit to https://scotch.io/tutorials/authenticate-a-node-js-api-with-json-web-tokens
apiRoutes.post('/authenticate', function (req, res) {
  User.findOne({
    username: req.body.username
  }, function (err, user) {
    if (err) {
      console.error('AUTH: Error:', req.body, err)
      res.status(400).send('AUTH: Error')
      return false
    }

    if (!user) {
      res.json({
        success: false,
        message: 'AUTH: Authentication failed. User not found.'
      })
    } else if (user) {
      // check if password matches
      if (user.password != req.body.password) {
        res.json({
          success: false,
          message: 'AUTH: Authentication failed. Wrong password.'
        })
      } else {
        // if user is found and password is right, create a token
        var token = jwt.sign(user, secret, {
          expiresIn: 1440 // expires in 24 hours
        })

        // return the information including token as JSON
        res.json({
          success: true,
          message: 'AUTH: Enjoy your token!',
          token: token,
          admin: user.admin
        })
      }
    }
  })
})


// VALIDATE TOKEN
// Credit to https://scotch.io/tutorials/authenticate-a-node-js-api-with-json-web-tokens
apiRoutes.use(function (req, res, next) {
  // check header or url parameters or post parameters for token
  var token = req.body.token || req.query.token || req.headers['x-access-token']

  // decode token
  if (token) {
    // verifies secret and checks exp
    jwt.verify(token, secret, function (err, decoded) {
      if (err) {
        return res.json({
          success: false,
          message: 'AUTH: Failed to authenticate token.'
        })
      } else {
        // if everything is good, save to request for use in other routes
        req.decoded = decoded;
        next();
      }
    });
  } else {
    // if there is no token, return an error
    return res.status(403).send({
      success: false,
      message: 'AUTH: No token provided.'
    })
  }
})


// TESTING: GET ALL USERS
apiRoutes.get('/users', function (req, res) {
  User.find({}, function (err, users) {
    res.json(users)
  })
})


// TESTING: POST A NEW USER
apiRoutes.post('/users', function (req, res) {
  let user = new User({
    name: req.body.name,
    username: req.body.username,
    password: req.body.password,
    admin: req.body.admin
  })

  user.save(function (err) {
    if (err) throw err
    res.json(user)
  })
})


// GET ALL ENTRIES
apiRoutes.get('/entries', function (req, res) {
  Entry
    .find({})
    .sort({
      'starttime': -1
    })
    .exec(function (err, entries) {
      if (err) {
        console.error('GET: Error:', req.body, err)
        res.status(400).send('GET: Error')
        return false
      }

      console.log("GET: Returned:", entries)
      res.status(200).json(entries)
    })
})


// GET ENTRIES BY USER
apiRoutes.get('/entries/user=:user', function (req, res) {
  Entry
    .find({
      user: req.params.user
    })
    .sort({
      'starttime': -1
    })
    .exec(function (err, entries) {
      if (err) {
        console.error('GET: Error:', req.body, err)
        res.status(400).send('GET: Error')
        return false
      }

      console.log("GET: Returned:", entries)
      res.status(200).json(entries)
    })
})


// POST A NEW ENTRY
apiRoutes.post('/entries', (req, res) => {
  let entry = new Entry({
    user: req.body.user,
    starttime: new Date(req.body.starttime),
    endtime: new Date(req.body.endtime),
    breaks: req.body.breaks,
    description: req.body.description
  })

  entry.save(function (err) {
    if (err) {
      console.error('POST: Error:', req.body, err)
      res.status(400).send('POST: Error')
      return false
    }

    console.log('POST: Created new entry:', entry);
    res.status(200).json({
      success: true
    })
  })
})


// UPDATE AN EXISTING ENTRY
apiRoutes.put('/entries/id=:_id', (req, res) => {
  const entry = req.body;
  const _id = req.params._id;

  Entry.update({
    _id: _id
  }, entry, function (err, entry) {
    if (err) {
      console.error('PUT: Error:', req.body, err)
      res.status(400).send('PUT: Error')
      return false
    }

    console.log('PUT: Updated entry:', entry)
    res.status(200).json(entry)
  })
})


// DELETE AN ENTRY
apiRoutes.delete('/entries/id=:_id', (req, res) => {

  Entry.findByIdAndRemove(req.params._id, function (err, entry) {
    if (err) {
      console.error('DELETE: Error:', req.body, err)
      res.status(400).send('DELETE: Error')
      return false
    }

    console.log('DELETE: Deleted entry:', entry)
    res.status(200).json(entry)
  })
})


module.exports = apiRoutes