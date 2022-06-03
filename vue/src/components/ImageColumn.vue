<template>
  <div class="my-images">
    <div class="feed-post-container" v-for="post in posts" v-bind:key="post.postId" v-bind:postId="post.postId">
     
      <img class="image" v-bind:src="post.img" alt="" />
      <post-interaction v-bind:post="post"/>
    
    </div>
  </div>
</template>

<script>
import postService from "@/services/PostService.js";
import PostInteraction from './PostInteraction.vue';
export default {
  components: { PostInteraction },
  name: "image-column",
  computed: {
    posts() {
      return this.$store.state.posts.filter((post) => {
        if (post.img.includes("cloudinary")) return post;
      });
    },
  },
  created() {
    // if(this.$store.accountId == -1){
      postService.getAllPosts().then((response) => {
      this.$store.commit("INITIALIZE_POSTS", response.data);
    })
    // } else {postService.getAccountPosts(this.$store.accountId).then((response) => {
    //   this.$store.commit("INITIALIZE_POSTS", response.data);
    // })
    // }
      }
    };
</script>

<style>
@media (max-width: 10000px) {
  .my-images {
    margin-left: 295px;
    line-height: 0;
    height: 100%;

    -webkit-column-count: 3;
    -webkit-column-gap: 0px;
    -moz-column-count: 3;
    -moz-column-gap: 0px;
    column-count: 3;
    column-gap: 0px;
    background: var(--primary-background-color);
  }
}

@media only screen and (max-width: 800px) {
  .my-images {
    margin-left: 295px;
    line-height: 0;
    height: 100%;

    background: var(--primary-background-color);
  }
}

@media only screen and (max-width: 1400px) {
  .my-images {
    margin-left: 295px;
    line-height: 0;
    height: 100%;

    -webkit-column-count: 2;
    -webkit-column-gap: 0px;
    -moz-column-count: 2;
    -moz-column-gap: 0px;
    column-count: 2;
    column-gap: 0px;
    background: var(--primary-background-color);
  }
}

.my-images .feed-post-container {
  margin-left: 0px;
  margin-right: 0px;
  width: calc(100% - 40px) !important;
  height: auto !important;
  box-shadow: 0px 0px 5px rgb(172, 169, 169);
  border-radius: 5%;

  /* margin-top: 5px; */
  border-top: 0px;
}

.my-images .feed-post-container > .image {
  width: calc(100%) !important;
}

.post-interaction-bar {
  background: white;

  box-shadow: 0px 0px 5px rgb(172, 169, 169);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 20px;
  margin-bottom: 5px;
  color: black;
}

.interaction-icon {
  max-height: auto;
  max-width: 20px;
}

.like-icon {
}

.image-info {
  border-left: solid black 3px;
  /* max-width: 20%; */
  color: black;
  padding-left: 5px;
}
</style>