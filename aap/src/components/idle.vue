<template>
  <div>

    <button @click="a">点击按钮lalalal</button>
  </div>
</template>

<script>
export default {
  data () {
    return {

    }
  },
  methods: {
    a () {
      this.$axios.get('/b').then(response => {
        // eslint-disable-next-line no-console
        console.log("hello" + response.data);
      })
    }
  }
}
</script>
