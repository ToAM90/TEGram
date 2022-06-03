<template>
  <div class="post-interaction-bar">
    <div @click="toggleLike(post.postId, post.liked)">
      <img
        class="like-icon interaction-icon"
        v-if="post.liked == true"
        src="@/resources/icons8-heart-50 (outline).png"
        alt=""
      />

      <img
        class="like-icon interaction-icon"
        v-else
        src="@/resources/icons8-heart-50 (outline).png"
        alt=""
      />
    </div>
    <router-link v-bind:to="{ name: 'post', params: { id: post.postId } }">
      <img
        class="view-more interaction-icon"
        src="@/resources/expand_more_FILL0_wght400_GRAD0_opsz48.png"
        alt=""
      />
    </router-link>
    <img
      class="favorite-icon interaction-icon"
      src="@/resources/icons8-star-50 (outline).png"
      alt=""
    />
  </div>
</template>

<script>
import likeService from "@/services/LikeService.js";

export default {
  name: "post-interaction",
  props: {
    post: {
      postId: Number,
      accountId: Number,
      img: String,
      postDate: Date,
      privated: Boolean,
      likeCount: Number,
    },
  },
  methods: {
    toggleLike(postId, liked) {
      console.log(postId);
      console.log(liked);
      if (liked == false) {
        likeService.likePost(postId);
        // this.$store.commit("TOGGLE_LIKE", postId);
      } else if (liked == true) {
        likeService.unlikePost(postId);
        // this.$store.commit("TOGGLE_LIKE", postId);
      }
    },
  },
};
</script>

<style>
</style>