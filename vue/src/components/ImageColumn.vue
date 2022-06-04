<template>
  <div class="my-images">
    <div
      class="feed-post-container"
      v-for="post in posts"
      v-bind:key="post.postId"
      v-bind:postId="post.postId"
    >
      <img class="image" v-bind:src="post.img" alt="" />
      <post-interaction v-bind:post="post" />
    </div>
  </div>
</template>

<script>
import postService from "@/services/PostService.js";
import PostInteraction from "./PostInteraction.vue";
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
    if (this.$store.state.account.accountId == -1) {
      postService.getAllPosts().then((response) => {
        this.$store.commit("INITIALIZE_POSTS", response.data);
      });
    } else {
      postService
        .getAccountPosts(this.$store.state.account.accountId)
        .then((response) => {
          this.$store.commit("INITIALIZE_POSTS", response.data);
        });
    }
  },
};
</script>

<style>
@media (max-width: 10000px) {
  .my-images {
    line-height: 0;
    height: calc(100%);

    -webkit-column-count: 3;
    -webkit-column-gap: 0px;
    -moz-column-count: 3;
    -moz-column-gap: 0px;
    column-count: 3;
    column-gap: 20px;
    background: var(--primary-background-color);
  }
}

@media only screen and (max-width: 800px) {
  .my-images {
    display: flex;
    flex-direction: column;

    background: var(--primary-background-color);
  }
}

@media only screen and (max-width: 1400px) {
  .my-images {
    line-height: 0;
    height: calc(100%);

    -webkit-column-count: 2;
    -webkit-column-gap: 0px;
    -moz-column-count: 2;
    -moz-column-gap: 0px;
    column-count: 2;
    column-gap: 20px;
    background: var(--primary-background-color);

    display: block;

    break-inside: avoid;
  }
}

.my-images .feed-post-container {
  box-shadow: 0px 0px 5px rgb(172, 169, 169);
  border-radius: 5%;
  border-top: 0px;
  display: block;

  break-inside: avoid;
}

.my-images .feed-post-container .image {
  width: calc(100%) !important;
}
</style>