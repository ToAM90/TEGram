import axios from 'axios';

export default {

    addComment(comment, postId) {
        return axios.post('/comments', comment, postId)
    },

    removeComment(commentId) {
        return axios.delete(`/comments/${commentId}`)
    }

}