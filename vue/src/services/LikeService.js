import axios from 'axios';

export default {

    likePost(postId) {
        return axios.put(`/like/${postId}`, postId)
    },

    unlikePost(postId) {
        return axios.delete(`/like/${postId}`, postId)
    }

}