<template>
  <div class="post-interaction-bar">
    <div @click="toggleLike(post.postId, post.liked)">
      <img
        class="like-icon interaction-icon"
        v-if="post.liked == true"
        src="@/assets/icons8-heart-50-outline.png"
        alt=""
      />

      <img
        class="like-icon interaction-icon"
        v-else
        src="@/assets/icons8-heart-50-outline.png"
        alt=""
      />
    </div>
    <router-link v-bind:to="{ name: 'post', params: { id: post.postId } }">
      <img
        class="view-more interaction-icon"
        src="@/assets/expand_more_FILL0_wght400_GRAD0_opsz48.png"
        alt=""
      />
    </router-link>
    <img
      class="favorite-icon interaction-icon"
      src="@/assets/icons8-star-50-outline.png"
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
.post-interaction-bar {
  background: var(--panel-background-color);

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