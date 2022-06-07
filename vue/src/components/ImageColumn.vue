<template>
  <div class="my-images">
    <div
      class="feed-post-container"
      v-for="(post, index) in filterPosts(this.$store.state.posts)"
      :key="index"
      v-bind:postId="post.postId">
      <div class="post-card" @click="selectCurrentPost(post)">
        <post-header id="username-header" :accountId="post.accountId" v-if="post.accountId" />
        <img class="image" v-bind:src="post.img" alt="" />
        <post-interaction v-bind:post="post" />
      </div>
      <div v-if="$store.state.currentPost.postId == post.postId">
        <post-details />
      </div>
    </div>

    <div
      v-if="this.$store.state.currentView == 'profile'"
      id="profile-add-new-post feed-post-container"
    ></div>
  </div>
</template>

<script>
import PostInteraction from "./PostInteraction.vue";
import PostHeader from "./PostHeader.vue";
import PostDetails from "./PostDetails.vue";

export default {
  components: { PostInteraction, PostHeader, PostDetails },
  name: "image-column",
  methods: {
    filterPosts(posts) {
      return posts.filter((post) => {
        if (post.img.includes("cloudinary")) return post;
      });
    },
    toggleDetails() {
      this.showDetails = !this.showDetails;
    },
    selectCurrentPost(post) {
      if (this.$store.state.currentPost.postId === 0) {
        this.$store.commit("SET_CURRENT_POST", post);
      } else if (
        this.$store.state.currentPost.postId !== 0 &&
        post.postId === this.$store.state.currentPost.postId
      ) {
        this.$store.commit("SET_CURRENT_POST", this.defaultPost);
      } else {
        this.$store.commit("SET_CURRENT_POST", post);
      }
    },
  },
  data() {
    return {
      showDetails: false,
      defaultPost: {
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
    };
  },
  computed: {},
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
    column-gap: 10px;
    /* background: var(--primary-background-color);s */
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
    column-gap: 10px;
    /* background: var(--primary-background-color); */

    display: block;

    break-inside: avoid;
  }
}

@media only screen and (max-width: 900px) {
  .my-images {
    display: flex;
    flex-direction: column;

    /* background: var(--primary-background-color); */
  }
}

.my-images .feed-post-container {
  box-shadow: 0px 0px 5px rgb(172, 169, 169);
  border-radius: 5%;
  border-top: 0px;
  display: block;

  break-inside: avoid;
}

.post-card {
  display: flex;
  flex-direction: column;
  gap: 0px;
  margin-bottom: 10px;
}

.my-images .feed-post-container .image {
  width: calc(100% - 10px) !important;
  border: 5px var(--panel-background-color) solid;
  /* border-radius: 5px 5px 0px 0px; */
}
</style>