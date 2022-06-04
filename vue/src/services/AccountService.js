import axios from 'axios';

export default {

    getAccountSelf() {
        return axios.get('/profile')
    },

    getAccountOther(username) {
        return axios.get(`/profile/${username}`)
    }

}