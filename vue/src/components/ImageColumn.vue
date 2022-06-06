<template>
  <div class="my-images">
    <div
      class="feed-post-container"
      v-for="(post, index) in filterPosts(this.$store.state.posts)"
      :key="index"
      v-bind:postId="post.postId"
    >
      <div @click="selectCurrentPost(post)">
        <post-header id="username-header" v-bind:accountId="post.accountId" />
        <img class="image" v-bind:src="post.img" alt="" />
        <post-interaction v-bind:post="post" />
        <div v-if="$store.state.currentPost.postId == post.postId">
          <post-details />
        </div>
      </div>
    </div>

    <div
      v-if="this.$store.state.currentView == 'profile'"
      id="profile-add-new-post feed-post-container"
    ></div>
  </div>
</template>

<script>
<<<<<<< HEAD
import PostInteraction from "./PostInteraction.vue";
import PostHeader from "./PostHeader.vue";
import PostService from "../services/PostService";
import AccountService from "../services/AccountService";
import PostDetails from "./PostDetails.vue";
=======
import PostInteraction from './PostInteraction.vue';
import PostHeader from './PostHeader.vue';
>>>>>>> 999ad3e59b4c8e9b7a2c67fa86e04759d5241e3b

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
      } else {
        this.$store.commit("SET_CURRENT_POST", this.defaultPost);
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
<<<<<<< HEAD
  created() {
    if (this.$route.params.id != undefined) {
      PostService.getAccountPosts(this.$route.params.id).then((response) =>
        this.$store.commit("INITIALIZE_POSTS", response.data)
      );
      AccountService.getAccountOther(this.$route.params.id).then((response) =>
        this.$store.commit("SET_ACCOUNT", response.data)
      );
    } else {
      PostService.getAllPosts().then((response) => {
        this.$store.commit("INITIALIZE_POSTS", response.data);
        AccountService.getAccountSelf().then((response) => {
          this.$store.commit("SET_CURRENT_ACCOUNT", response.data);
        });
      });
    }
  },
};
=======

     }

>>>>>>> 999ad3e59b4c8e9b7a2c67fa86e04759d5241e3b
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

.my-images .feed-post-container .image {
  width: calc(100% - 10px) !important;
  border: 5px var(--panel-background-color) solid;
  /* border-radius: 5px 5px 0px 0px; */
}
</style>