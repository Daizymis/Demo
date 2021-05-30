<!--
* @Author:Min Huang
* @Date: 2021/5/25
* @Desc: 模糊搜索/过滤
-->
<template>
  <div class="selector">
    <van-popup v-model="showPicker" position="bottom" :close-on-click-overlay="false" @click-overlay="close">
      <van-search v-model="searchText" placeholder="搜索" @input="onSearch"> </van-search>
      <van-picker show-toolbar :columns="showOptions" @confirm="onConfirm" @cancel="close" @change="onChange">
        <template #option="item">
          {{ getShowData(item) }}
        </template>
      </van-picker>
    </van-popup>
  </div>
</template>
<script>
export default {
  name: 'selector',
  props: {
    //下拉选项保存值
    searchValue: {
      type: String,
      default: 'id'
    },
    //下拉展示的字段，可以是单个属性，也可以是多个属性拼接
    // 单属性 label='name'|| 多属性 :label="['key', 'name']"
    searchLabel: {
      type: [String, Array],
      default: 'value'
    },
    showPicker: {
      type: Boolean,
      default: false
    },
    options: {
      type: Array,
      default: () => []
    },
    filterable: {
      type: Boolean,
      default: false
    },
    //是否为远程搜索
    remote: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      searchText: '',
      currentChoose: null,
      showOptions: [] //展示的下拉列表
    };
  },
  watch: {
    //模糊搜索时需同步更新下拉列表
    options(val) {
      if (this.remote) {
        this.showOptions = val;
      }
    }
  },
  created() {
    this.$nextTick(() => {
      this.showOptions = this.options.slice(0);
    });
  },
  methods: {
    /**
     * 搜索时
     */
    onSearch() {
      if (this.remote) {
        this.$emit('search', this.searchText);
      } else if (this.filterable) {
        this.onFilter();
      }
    },
    /**
     * 过滤
     */
    onFilter() {
      if (this.searchText === '' || this.searchText === null) {
        this.showOptions = this.options.slice(0);
      } else {
        this.showOptions = this.options.filter(item => this.getShowData(item).indexOf(this.searchText) > -1);
      }
    },
    /**
     * 选择器展示值
     * @param item
     * @returns {string|*}
     */
    getShowData(item) {
      if (item) {
        if (typeof this.searchLabel == 'string') {
          return item[this.searchLabel] ? item[this.searchLabel] : item.id;
        } else {
          let str = '';
          this.searchLabel.forEach(i => {
            str = str.concat(item[i] + ' ');
          });
          return str;
        }
      }
    },
    /**
     * 确认回调
     * @param value
     */
    onConfirm(value) {
      if (value) {
        if (this.searchValue === 'item') {
          this.currentChoose = value;
        } else {
          console.log(value);
          this.currentChoose = value[this.searchValue];
        }
        this.$emit('confirm', this.currentChoose);
      }
      this.$emit('close');
    },
    close() {
      this.$emit('close');
    },
    onChange(picker, value, index) {
      if (value) {
        if (this.searchValue === 'item') {
          this.currentChoose = value;
        } else {
          this.currentChoose = value[this.searchValue];
        }
        this.$emit('choose', this.currentChoose);
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.selector {
  .placehodler-text {
    color: #999999;
  }
}
.close-popup {
  position: absolute;
  right: 0.28rem;
  top: 0.3rem;
}
.search-dev {
  .van-cell {
    font-size: 0.28rem;
    line-height: 0.36rem;
  }
  background: #ffffff;
  position: relative;
  .search-text {
  }
}
</style>
