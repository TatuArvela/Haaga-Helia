import transport from '../utils/transport';

const login = {

  login(event) {
    event.preventDefault();
    transport.login(this);
  }

};

export default login;