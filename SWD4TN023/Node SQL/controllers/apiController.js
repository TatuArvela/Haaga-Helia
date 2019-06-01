module.exports = function (app, connection) {
  app.get('/api/person/:id', function (req, res) {
    connection.query('SELECT * FROM `persons` WHERE `id`= ?', [req.params.id], function (err, rows, fields) {
      if (err) throw console.error(err)

      if (rows.length > 0) {
        res.json({
          id: rows[0].Id,
          firstname: rows[0].FirstName,
          lastname: rows[0].LastName
        });
      }
      else {
        res.json({
          error: 'unused id'
        })
      }
    })
  });

  app.post('/api/person', function (req, res) {
    connection.query('INSERT INTO `persons` (FirstName, LastName) VALUES (?, ?)', [req.body.firstname, req.body.lastname], function (err, result) {
      if (err) throw console.error(err)
      res.sendStatus(200)
    })
  });

  app.delete('/api/person/:id', function (req, res) {
    connection.query('DELETE FROM `persons` WHERE Id = ?', [req.params.id], function (err, result) {
      if (err) throw console.error(err)
      res.sendStatus(200)
    })
  });
}