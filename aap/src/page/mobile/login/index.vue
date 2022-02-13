<template>
  <div>
    <!--<van-field
      readonly
      clickable
      name="picker"
      :value="value"
      label="选择器"
      placeholder="点击选择城市"
      @click="showPicker = true"
    />-->
    <van-form validate-first @failed="onFailed" @submit="onSubmit">
      <!--<fuzzy-search
        :show-picker="showPicker"
        :rules="[{ required: true, message: '请填写密码' }]"
        search-label="ename"
        search-value="item"
        :options="employList"
        @search="getEmployeeList"
        @confirm="confirmValue"
        @close="showPicker = false"
      ></fuzzy-search>
      <filter-select
        label="名称"
        placeholder="点击选择名称"
        remote
        s-label="ename"
        s-value="caid"
        :options="employList"
        @choose="changeValue"
        @confirm="value = $event"
        @search="getEmployeeList"
      ></filter-select>-->
      <filter-select
        label="名称"
        placeholder="点击选择名称"
        filterable
        s-label="name"
        s-value="item"
        :options="arr"
        @choose="changeValue"
        @confirm="value1 = $event"
      ></filter-select>
      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit" @click="showPicker = true">展示按钮</van-button>
      </div>
    </van-form>
  </div>
</template>
<script>
// import fuzzySearch from '@/mobileComponents/fuzzy-search/index';
import filterSelect from '@/components/select/my-select';
export default {
  name: 'Invoice',
  components: {
    // fuzzySearch,
    filterSelect
  },
  data() {
    return {
      showPicker: false,
      value: '',
      value1: '',
      value2: '',
      arr: [
        { key: '1', name: '21212' },
        { key: '2', name: '李232' },
        { key: '3', name: '李家琪' },
        { key: '4', name: '刘家家' },
        { key: '5', name: '34323' },
        { key: '6', name: '3231' }
      ],
      employList: []
    };
  },
  methods: {
    onSubmit(values) {
      c(values);
    },
    onFailed(errorInfo) {
      console.log('failed', errorInfo);
    },
    confirmValue(value) {
      c(value);
    },
    changeValue(value) {
      console.log(value);
    }
  }
};
</script>
