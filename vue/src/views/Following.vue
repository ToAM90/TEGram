<template>
  <main id="following-page">
    <side-bar id="side-bar-home" />
    <header class="follow-header">
      <h1 class="page-title-text">Following</h1>
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
        v-bind:key="account.accountId">
       
          <img class="account-image" :src="account.profileImg" />
          <p
            class="account-name"
            v-on:click="$router.push(`/profile/${account.accountId}`)">
            {{ account.displayName }}</p>

          <button
            v-on:click="toggleFollow(account.followed, account.accountId)">
            {{ account.followed ? "Unfollow" : "Follow" }}
          </button>
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
        this.$router.go(0);
      } else {
        FollowService.follow(accountId);
        this.$router.go(0);
      }
    },
  },
  created() {
    FollowService.listFollowing().then((response) => {
      this.$store.commit("SET_FOLLOW_ACCOUNTS", response.data);
    });
  },
};
</script>

<style>

</style>