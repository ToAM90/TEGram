<template>
  <div id="post-list">
      <div v-for="post in posts" v-bind:key="post.postId" v-bind:postId = post.postId>
      <router-link v-bind:to="{name: 'post', params: {id: post.postId}}"> {{post}} </router-link>
      </div>
  </div>
</template>

<script>
import postService from '../services/PostService';

export default {

    name: "postList",
    data(){
        return {
            posts: []
        }
    },
    created(){
        postService.getAllPosts().then((response) => {
            this.posts = response.data;
        })
    }

}
</script>

<style>

</style>