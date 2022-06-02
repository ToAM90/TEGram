import axios from 'axios';

export default {

    addPost(post) {
        return axios.post('/post', post)
    },

    getAllPosts(){
        return axios.get(`/posts`)
    },

    getPost(postId){
        return axios.get(`/post/${postId}`)
    },

    // addLike(postId){
    //     return axios.post(`/like/${postId}`)
    // },

    // removeLike(postId){
    //     return axios.delete(`/like/${postId}`)
    // }
}