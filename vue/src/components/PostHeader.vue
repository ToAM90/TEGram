<template>
  <div class="post-header-bar">
    <router-link
      class="display-name"
      v-bind:to="{ name: 'profile', params: { id: post.accountId } }"
    >
      <p class="display-name">{{ getDisplayName() }}{{ displayName }}</p>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "post-header",
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
  },
  data() {
    return {
      displayName: "",
    };
  },

  methods: {
    getDisplayName() {
      this.$store.state.accounts.find((stateAccount) => {
        if (stateAccount.accountId == this.post.accountId) {
          this.displayName = stateAccount.displayName;
        }
      });
    },
  },
};
</script>

<style>
.post-header-bar {
  /* background-image: url("https://res.cloudinary.com/dcipg5scy/image/upload/v1654570644/TE_GRAM/u7k1krgvbdjhnfujrwhy.png"); */
  background: #d1d1d3;
  box-shadow: 0px 0px 5px rgb(172, 169, 169);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 20px;
  border-radius: 4px 4px 0px 0px;
  padding-top: 5px;
  padding-bottom: 5px;
}

.display-name {
  margin: auto;
  /* padding: 8px; */

  color: #ff48a3;

  font-size: 1.3rem;
  font-family: "Montserrat", sans-serif;
  font-weight: 600;
}
</style>