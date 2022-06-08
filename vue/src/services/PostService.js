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

    getAccountPosts(accountId){
        return axios.get(`/${accountId}/posts`)
    },

    deletePost(postId){
        return axios.delete(`/post/${postId}`)
    }
}