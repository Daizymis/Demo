<template>
  <div class="keyboard"
       v-clickoutside="hide">
    <div class="keyboard__line">
      <div v-if=label
           class="keyboard__label">
        {{label}}
      </div>
      <input :type="type"
             data-layout="compact"
             :placeholder="placeholder"
             :maxlength="maxlength"
             @focus="show"
             @blur="submit">
    </div>
    <div class="keyboard__line">
      <div class="keyboard__label"
           v-if=label></div>
      <div class="keyboard__warn">{{warn}}</div>
    </div>
    <div v-if="visible"
         :class="isNormalPage?'mock-normal':'mock-sop'">
      <vue-touch-keyboard id="keyboard"
                          class="keyboard-nav acio-keyboard"
                          :layout="layout"
                          :options="options"
                          :cancel="hide"
                          :accept="hide"
                          :input="input" />
    </div>
  </div>
</template>

<script>
import VueTouchKeyboard from 'vue-touch-keyboard';
import 'vue-touch-keyboard/dist/vue-touch-keyboard.css';
import $ from 'jquery';

const clickoutside = {
  // 初始化指令

  bind(el, binding, vnode) {
    function documentHandler(e) {
      // 这里判断点击的元素是否是本身，是本身，则返回
      if (el.contains(e.target)) {
        return false;
      }
      // 判断指令中是否绑定了函数
      if (binding.expression) {
        // 如果绑定了函数 则调用那个函数，此处binding.value就是handleClose方法
        binding.value(e);
      }
    }
    // 给当前元素绑定个私有变量，方便在unbind中可以解除事件监听
    el.__vueClickOutside__ = documentHandler;
    document.addEventListener('click', documentHandler);
  },
  update() { },
  unbind(el, binding) {
    // 解除事件监听
    document.removeEventListener('click', el.__vueClickOutside__);
    delete el.__vueClickOutside__;
  },
};

export default {
  components: {
    'vue-touch-keyboard': VueTouchKeyboard.component,
  },
  directives: { clickoutside },
  data() {
    return {
      visible: false,
      options: {
        useKbEvents: false,
        preventClickEvent: false,
      },
      input: null,
      isWrong: true,
    };
  },
  props: {
    isNormalPage: { type: Boolean, default: true },
    maxlength: Number,
    placeholder: String,
    layout: String,
    label: { type: String, default: null },
    labelWidth: String,
    warn: { type: String, default: null },
    instanceID: String,
    type: {
      type: String, default: 'text',
    },
  },
  mounted() {
    this.changeLabelWidth();
  },
  methods: {
    hide() {
      this.visible = false;
    },
    submit() {
      if (this.input === null) {
        return;
      }
      this.$emit('data-change', this.input.value);
    },
    show(e) {
      this.input = e.target;
      if (!this.visible) { this.visible = true; }
    },
    changeLabelWidth() {
      $('.keyboard__label').css('width', this.labelWidth);
    },
  },
  watch: {
    warn: {
      handler() {
        const doubleId = `#${this.instanceID}`;
        if (this.warn !== '' && this.warn != null) {
          $(doubleId).find('input').css('border', '1px solid rgba(255, 0, 90, 1)');
        } else {
          $(doubleId).find('input').css('border', 'none');
        }
      },
    },
  },
};
</script>
<style scoped lang="scss">
.keyboard {
  &__line {
    display: flex;
    flex-flow: row nowrap;
    align-items: center;
  }
  &__label {
    width: 20%;
    display: flex;
    justify-content: flex-end;
    flex-shrink: 0;
    font-size:22px;
    padding-right: 22px;
    color: #738aa7;
  }
  &__warn {
    font-size: 22px;
    height: 44px;
    padding-top: 4px;
    color: rgba(255, 0, 90, 1);
  }
}
.mock-normal {
  background: rgba(0, 0, 0, 0.6);
  border-radius: 8px;
  position: fixed;
  bottom: 0;
  left: 271px;
  width: 1042px;
  height: 313px;
  padding-top: 24px;
  font-size: 22px !important;
  display: flex;
  flex-flow: row nowrap;
  justify-content: center;
}
.mock-sop {
  background: rgba(0, 0, 0, 0.6);
  border-radius: 8px;
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 48%;
  padding-top: 2%;
  font-size: 22px !important;
  display: flex;
  flex-flow: row nowrap;
  justify-content: center;
}

input {
  height: 66px;
  border: none;
  background: rgba(0, 65, 119, 0.08);
  font-size: 22px;
  border-radius: 5px;
  transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
  text-indent: 21px;

  &--error {
    border: 1px solid rgba(255, 0, 90, 1);
    box-shadow: 0px 0px 6px 0px rgba(255, 0, 90, 0.75);
  }
  :placeholder-shown {
    color: #999999;
  }

  :-ms-input-placeholder {
    color: #eeeeee !important;
  }
}
.keyboard-nav {
  width: 80%;
}
.acio-keyboard .keyboard .line .key {
  background-color: #666;
}
</style>
<style lang="scss">
.acio-keyboard .keyboard .line .key {
  background-color: #777;
  color: white;
  border: none;
  font-size:22px;
  height: 60px;
  line-height: 60px;
}
.acio-keyboard .keyboard .key:not(:last-child) {
  margin-right: 8px;
}
.acio-keyboard .keyboard .line:not(:last-child) {
  margin-bottom: 8px;
}
.acio-keyboard .keyboard .key.featured {
  color: #fff;
  background: linear-gradient(
    -90deg,
    rgba(10, 96, 226, 1) 0%,
    rgba(56, 169, 234, 1) 100%
  );
  border-color: #2e6da4;
}
.acio-keyboard .keyboard .key.control {
  color: #fff;
  background-color: #222;
  border: none;
  box-shadow: 0px 0px 20px 0px rgba(12, 8, 8, 0.1);
}
.acio-keyboard .keyboard .key:hover {
  background-color: #aaa;
}
</style>
