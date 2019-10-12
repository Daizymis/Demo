<template>
  <div id="home">

  </div>
</template>

<script>

export default {
  components: {

  },
  data () {
    return {
      nowDate: ''
    }
  },
  methods: {

  },

  created () {

    this.$nextTick(() => {
      this.$refs.timeoutRef.timeoutChange(20);
    })
  }


}
</script>

<style lang="less">
</style>
