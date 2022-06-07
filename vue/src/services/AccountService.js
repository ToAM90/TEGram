import axios from 'axios';

export default {

    getAccountSelf() {
        return axios.get('/profile')
    },

    getAccountOther(accountId) {
        return axios.get(`/profile/${accountId}`)
    },

    updateAccount(account){
        return axios.put('/profile', account)
    }


}