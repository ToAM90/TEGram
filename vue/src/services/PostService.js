import axios from 'axios';

export default {

    addPost(post) {
        return axios.post('/post', post)
    },

    getAllPosts() {
        return axios.get(`/posts`)
    },

    getPost(postId) {
        return axios.get(`/post/${postId}`)
    },
    //remove + add to list of likes
    //ex call for a like:
    //postId, userId can be simplified by passing in entire like obj
    //LikeService.likePost(postId)
    //PostService.addLike(postId, userId)
    //this.$state.commit('ADD_LIKE', postId, userId)
}