<template>
<div>
   <side-bar id="side-bar-home"/>
  <div id="follow-list">
    <h1 class="follow-header">Following</h1>
    <div
      id="list-accounts"
      v-for="account in $store.state.followAccounts"
      v-bind:key="account.accountId"
    >
    <div id="account-item">
      <img class="account-image" :src="account.profileImg" />
      <span class="account-name" v-on:click="$router.push(`/profile/${account.accountId}`)">
        {{ account.displayName }}
      </span>

      <button v-on:click="toggleFollow(account.followed, account.accountId)"> {{account.followed ? "Unfollow" : "Follow"}} </button>
      <span class="account-biography">{{account.biography}}</span>
    </div>
    </div>
  </div>
  </div>
</template>

<script>
import FollowService from "../services/FollowService";
import SideBar from "../components/SideBar.vue";

export default {
  name: "following",
    components: {
    SideBar,
  },
  methods: {
    toggleFollow(followed, accountId){
      if(followed){
        FollowService.unfollow(accountId);
        this.$router.go(0);
        } else {
          FollowService.follow(accountId);
          this.$router.go(0);
        }
    }
  },
  created() {

        FollowService.listFollowing().then((response) => {
             this.$store.commit("SET_FOLLOW_ACCOUNTS", response.data)
        })

    }
};
</script>

<style>

#follow-list{
  width: calc(100% - 315px);
  margin-left: 35%;
  margin-right: auto;
  padding-top: 20px;
}

.follow-header{
}

#list-accounts{
display: flex;
align-items: center;
}

#list-accounts p:nth-of-type(even){
color: salmon;
}

#list-accounts p:nth-of-type(odd){
  background-color: salmon;
}

.account-image{
  margin-top: 35px;
  width: 150px;
  height: 150px;
  padding: 0px;
  border-radius: 100%;
}
</style>