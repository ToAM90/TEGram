import axios from 'axios';

export default {

    addComment(comment) {
        return axios.post(`/comments`, comment)
    },

    removeComment(commentId) {
        return axios.delete(`/comments/${commentId}`)
    }

}