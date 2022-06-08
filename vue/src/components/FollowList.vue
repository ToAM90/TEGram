<template>
  <div id="follow-list">
    <h1>{{this.$store.state.followView ? "Following" : "Followers"}} {{numAccounts}}</h1>
    <div
      id="list-accounts"
      v-for="account in accounts"
      v-bind:key="account.accountId"
    >
      <img class="account-image" :src="account.profileImg" />
      <span class="account-name" v-on:click="$router.push(`/profile/${account.accountId}`)">
        {{ account.displayName }}
      </span>

      <button v-on:click="toggleFollow(account.followed)"> {{account.followed ? "Unfollow" : "Follow"}} </button>
      <span class="account-biography">{{account.biography}}</span>
    </div>
  </div>
</template>

<script>
import FollowService from "../services/FollowService";

export default {
  name: "following",
  data() {
    return {
      accounts: [],
    };
  },
  computed:{
      numAccounts(){
       return this.accounts.length;
          
      }
  },
  methods: {
    toggleFollow(followed){
      if(followed){
        FollowService.unfollow().then((response) => {
          return response;
        })
      } else {
        FollowService.follow().then((response) => {
          return response;
        })
      }
    }
  },
  created() {

    if(this.$store.state.followView){
        FollowService.listFollowing().then((response) => {
      this.accounts = response.data;
    });
    } else{
        FollowService.listFollowers().then((response) => {
            this.accounts = response.data;
        })

    }
    
  },
};
</script>

<style>

#follow-list{
  width: calc(100% - 315px);
  margin-left: 295px;
  margin-right: auto;
  padding-top: 20px;
}
</style>