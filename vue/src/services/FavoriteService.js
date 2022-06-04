import axios from 'axios';

export default {

    favoritePost(postId) {
        return axios.post(`/favorite/${postId}`)
    },

    unfavoritePost(postId) {
        return axios.delete(`/favorite/${postId}`)
    },

    getFavoritePosts(){
        return axios.get('/favorites')
    }

}