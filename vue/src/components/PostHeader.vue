<template>
  <div class="post-header-bar">
    <router-link
      class="display-name"
      v-bind:to="{ name: 'profile', params: { id: post.accountId } }"
    >
      <p class="display-name">{{ getDisplayName() }}{{ displayName }}</p>
    </router-link>
    <button v-if="post.accountId != $store.state.currentAccount.accountId" class="follow-btn" v-on:click="toggleFollow()">{{getFollow()}} {{follow? 'Unfollow' : 'Follow'}}</button>
  </div>
</template>

<script>
import FollowService from '../services/FollowService';

export default {
  name: "post-header",
  props: {
    post: {},
  },
  data() {
    return {
      displayName: "",
      follow: ""
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
    getFollow(){
      this.$store.state.accounts.find((stateAccount) => {
        if (stateAccount.accountId == this.post.accountId) {
          this.follow = stateAccount.followed;
        }
      });
    },
    toggleFollow(){
      if(this.follow){
          FollowService.unfollow(this.post.accountId)
          this.$router.go(0)
      } else {
        FollowService.follow(this.post.accountId)
        this.$router.go(0)
      }
    }
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
.follow-btn{
  width: 10000px;
}
</style>