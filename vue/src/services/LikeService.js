import axios from 'axios';

export default {

    likePost(postId) {
        return axios.post(`/like/${postId}`)
    },

    unlikePost(postId) {
        return axios.delete(`/like/${postId}`)
    },

    getLikedPosts(){
        return axios.get('/liked')
    }

}