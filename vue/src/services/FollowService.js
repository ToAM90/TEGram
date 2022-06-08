import axios from 'axios';

export default {

    follow(followingId){
        return axios.post(`/follow/${followingId}`)
    },

    unfollow(followingId){
        return axios.delete(`/follow/${followingId}`)
    },

    listFollowing(){
        return axios.get('/following')
    },
     
    listFollowers(){
        return axios.get('/followers')
    }
}