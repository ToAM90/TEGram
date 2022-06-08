import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  update(user) {
    return axios.put('/update', user)
  },

  getUser(){
    return axios.get('/user')
  }
}
