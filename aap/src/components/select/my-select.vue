<!--
* @Author:Min Huang
* @Date: 2021/5/26
* @Desc: 选择器（field+ picker）
-->
<template>
  <div class="my-select">
    <van-field v-model="value" v-bind="$attrs" readonly clickable name="picker" @click="showPicker = true"> </van-field>
    <fuzzy-search
      v-bind="$attrs"
      :search-label="sLabel"
      :search-value="sValue"
      :show-picker="showPicker"
      v-on="$listeners"
      @confirm="onConfirm"
      @close="showPicker = false"
    ></fuzzy-search>
  </div>
</template>
<script>
import fuzzySearch from './index';
export default {
  name: 'FilterSelect',
  components: {
    fuzzySearch
  },
  props: {
    //下拉选项保存值
    sValue: {
      type: String,
      default: 'id'
    },
    //下拉展示的字段，可以是单个属性，也可以是多个属性拼接
    // 单属性 label='name'|| 多属性 :label="['key', 'name']"
    sLabel: {
      type: [String, Array],
      default: 'value'
    }
  },
  data() {
    return {
      showPicker: false,
      value: '',
      showOptions: [],
      searchText: ''
    };
  },
  created() {},
  methods: {
    /**
     * 获取展示字段
     * @param item
     * @returns {string|*}
     */
    getShowData(item) {
      if (item) {
        if (typeof this.sLabel == 'string') {
          return item[this.sLabel];
        } else {
          let str = '';
          this.sLabel.forEach(i => {
            str = str.concat(item[i] + ' ');
          });
          return str;
        }
      }
    },
    /**
     * 确认 将值传入父组件
     * @param value
     * @param index
     */
    onConfirm(value) {
      this.value = this.getShowData(value);
    },
    close() {
      this.showPicker = false;
    }
  }
};
</script>

<style lang="scss" scoped>
.my-select {
  .placehodler-text {
    color: #999999;
  }
}
.close-popup {
  position: absolute;
  right: 0.28rem;
  top: 0.3rem;
}
</style>
