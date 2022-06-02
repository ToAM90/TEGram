import axios from 'axios';

export default {

    addPost(post) {
        return axios.post('/post', post)
    }
}