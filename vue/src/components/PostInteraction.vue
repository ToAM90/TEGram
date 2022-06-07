<template>
  <div class="post-interaction-bar">
    <div class="like-icon" @click.stop="toggleLike(post.postId, post.liked)">
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
        <img
          class="interaction-icon image_off"
          src="@/assets/icons8-heart.gif"
          alt="logo"
        />
      </div>
      <span class="likes-count">{{ post.likesCount }}</span>
    </div>

    <img
      class="view-more interaction-icon"
      src="@/assets/expand_more_FILL0_wght400_GRAD0_opsz48.png"
    />

    <div
      class="favorite-icon"
      @click.stop="toggleFavorite(post.postId, post.favorited)"
    >
      <img
        class="interaction-icon image_on"
        v-if="post.favorited"
        src="@/assets/icons8-star-50.png"
        alt=""
      />
      <img
        class="interaction-icon image_on"
        v-else
        src="@/assets/icons8-star-50-outline.png"
        alt=""
      />
      <img
        class="interaction-icon image_off"
        src="@/assets/icons8-star.gif"
        alt="logo"
      />
    </div>
  </div>
</template>

<script>
import LikeService from "../services/LikeService.js";
import FavoriteService from "../services/FavoriteService.js";

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
      likesCount: 0,
    },
    data() {
      return {};
    },
  },
  methods: {
    toggleLike(postId, liked) {
      if (liked == false) {
        LikeService.likePost(postId);
        this.$store.commit("TOGGLE_LIKE", postId);
      } else if (liked == true) {
        LikeService.unlikePost(postId);
        this.$store.commit("TOGGLE_LIKE", postId);
      }
    },
    toggleFavorite(postId, favorited) {
      if (favorited == false) {
        FavoriteService.favoritePost(postId);
        this.$store.commit("TOGGLE_FAVORITE", postId);
      } else if (favorited == true) {
        FavoriteService.unfavoritePost(postId);
        this.$store.commit("TOGGLE_FAVORITE", postId);
      }
    },
  },
};
</script>

<style>
.post-interaction-bar {
  background-image: url("https://res.cloudinary.com/dcipg5scy/image/upload/v1654570644/TE_GRAM/u7k1krgvbdjhnfujrwhy.png");
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  color: black;
  border-radius: 0px 0px 4px 4px;
  height: 30px;
}

.interaction-icon {
  max-height: auto;
  padding-top: 5px;
  max-width: 20px;
  margin-left: 5px;
  margin-right: 5px;
}

.like-icon {
  display: flex;
  justify-content: space-between;

  cursor: pointer;
}

.likes-count {
  margin: auto;
  padding-left: 2px;
}

.image-info {
  border-left: solid black 3px;
  color: black;
  padding-left: 5px;
}

.image_off,
.like-icon:hover .image_on,
.favorite-icon:hover .image_on {
  display: none;
}
.image_on,
.like-icon:hover .image_off,
.favorite-icon:hover .image_off {
  display: block;
}
</style>