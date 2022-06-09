<template>
  <main id="following-page">
    <side-bar id="side-bar-home" />
    <header class="follow-header">
      <h1 class="page-title-text">Follower</h1>
      <img
        class="page-title-icon"
        src="@/assets/groups_2_FILL0_wght400_GRAD0_opsz48.png"
        alt=""
      />
    </header>

    <div id="follow-list">
      <div
        id="list-accounts"
        v-for="account in $store.state.followAccounts"
        v-bind:key="account.accountId"
      >
        <router-link
          v-bind:to="{ name: 'profile', params: { id: account.accountId } }"
        >
          <img class="account-image" :src="account.profileImg" />
        </router-link>
        <span v-on:click="toggleFollow(account.followed, account.accountId)">
        <img class="follow-icon" v-if="account.followed" src="@/assets/delete-friend.png" />
      <img class="follow-icon" v-else src="@/assets/add-friend.png" />
        <p
          class="account-name"
          v-on:click="$router.push(`/profile/${account.accountId}`)"
        >
          {{ account.displayName }}
          
        </p>

      
</span>
      </div>
    </div>
  </main>
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
    toggleFollow(followed, accountId) {
      if (followed) {
        FollowService.unfollow(accountId);
        this.$store.commit("TOGGLE_FOLLOW_FOLLOW", accountId);
      } else {
        FollowService.follow(accountId);
        this.$store.commit("TOGGLE_FOLLOW_FOLLOW", accountId);
      }
    },
  },
  created() {
    FollowService.listFollowers().then((response) => {
      this.$store.commit("SET_FOLLOW_ACCOUNTS", response.data);
    });
  },
};
</script>

<style>



@media only screen and (max-width: 1400px){
  #follow-list{
    display: block;
    line-height: 0;
    height: calc(100%);
    -webkit-column-count: 2;
    -webkit-column-gap: 0px;
    -moz-column-count: 2;
    -moz-column-gap: 0px;
    column-count: 2;
    column-gap: 10px;
    break-inside: avoid;
  }
}

@media only screen and (max-width: 900px){
  #follow-list{
     display: flex;
     flex-direction: column;
  }
}
#follow-list { 
    line-height: 0;
    height: calc(100%);
    -webkit-column-count: 3;
    -webkit-column-gap: 0px;
    -moz-column-count: 3;
    -moz-column-gap: 0px;
    column-count: 3;
    column-gap: 10px;
  margin-left: 10%;
  margin-right: auto;

  max-width: 100%;
 
}

.follow-header {
  margin-left: 15%;
  margin-right: auto;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}
.page-title-text {
  color: beige;
}
.page-title-icon {
   background: beige;
  margin-left: 10px;
  border-radius: 4px;
  height: 32px;
}

#list-accounts {
  display: block;
  break-inside: avoid;
  font-style: "Open Sans", sans-serif;
  text-align: center;
}

#list-accounts:nth-of-type(odd) {
  color: var(--sign-up-blue);
}

#list-accounts:nth-of-type(even) {
  color: salmon;
}

.account-image {
  margin-top: 35px;
  width: 150px;
  height: 150px;
  padding: 0px;
  border-radius: 100%;
}

#list-accounts .follow-icon{
  background: beige;
  margin-left: -10px
}
</style>