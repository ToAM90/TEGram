<template>
  <div class="post-header-bar">
      <router-link class="display-name" v-bind:to="{name: 'profile', params: {id: accountId}}">
      {{account.displayName}}
      </router-link>
  </div>
</template>

<script>
import AccountService from '../services/AccountService'
export default {
    name: "post-header",
    props: {accountId: Number},
    data(){
        return {
            account: {
                accountId: 0,
                displayName: ""
            }
          
        }
    },
    created(){
        AccountService.getAccountOther(this.accountId).then((response) => 
        this.account = response.data)
    }    
    }
</script>

<style>
.post-header-bar {
  background: var(--panel-background-color);
  box-shadow: 0px 0px 5px rgb(172, 169, 169);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 20px;
  color: black;
}

.display-name{
    margin: auto;
}

</style>