<template>
  <div class="post-interaction-bar">
    <div class="like-icon" @click="toggleLike(post.postId, post.liked)">
      <div>
         <img
        class="interaction-icon image_on"
        v-if="post.liked"
        src="@/assets/icons8-heart-50.png"
        alt=""
      />
      <img
        class="interaction-icon image_on"
        v-else
        src="@/assets/icons8-heart-50-outline.png"
        alt=""
      /> 
      <img class="interaction-icon image_off" src="@/assets/icons8-heart.gif" alt="logo" />
      </div>
      <div class="likes-count">{{post.likesCount}}</div>
    </div>
    
    <div>
    <router-link v-bind:to="{ name: 'post', params: { id: post.postId } }">
      <img
        class="view-more interaction-icon"
        src="@/assets/expand_more_FILL0_wght400_GRAD0_opsz48.png"
        alt=""
      />
    </router-link>
    </div>
    <div>
    <img
      class="favorite-icon interaction-icon"
      src="@/assets/icons8-star-50-outline.png"
      alt=""
    />
    </div>
  </div>
</template>

<script>
import likeService from "@/services/LikeService.js";

export default {
  name: "post-interaction",
  props: {
    post: {
      postId: 0,
      accountId: 0,
      img: "",
      caption: "",
      postDate: "",
      privated: false,
      comments: [],
      liked: false,
      favorited: false,
      likesCount: 0
    },
  },
  methods: {
    toggleLike(postId, liked) {
      if (liked == false) {
        likeService.likePost(postId);
        this.$store.commit("TOGGLE_LIKE", postId);
      } else if (liked == true) {
        likeService.unlikePost(postId);
        this.$store.commit("TOGGLE_LIKE", postId);
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
  display: flex;
  justify-content: space-between;
  cursor: pointer;
}

.likes-count{
  margin: auto;
  padding-left: 2px;
}

.image-info {
  border-left: solid black 3px;
  /* max-width: 20%; */
  color: black;
  padding-left: 5px;
}

.image_off, .like-icon:hover .image_on{
  display: none;
}
.image_on, .like-icon:hover .image_off{
  display:block;
}

</style>