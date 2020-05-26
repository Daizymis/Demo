<template>
  <div class="">
    <div id="map"></div>
    <input id="roomnumber"
           name="roomnumber"
           placeholder="请输入房间号"
            class="input-style">
      <button @click="confirm"
              class="btn-style">确定</button>
      <button @click="renderMap"
              class="btn-style">渲染</button>
      <button @click="exportFile"
              class="btn-style">导出文件</button>
  </div>
</template>

<script>
import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import Feature from 'ol/Feature';
import { getCenter } from 'ol/extent';
import ImageLayer from 'ol/layer/Image';
import Projection from 'ol/proj/Projection';
import Static from 'ol/source/ImageStatic';
import { Vector as VectorLayer } from 'ol/layer';
import { Vector as VectorSource } from 'ol/source';
import Polygon from 'ol/geom/Polygon';
import GeoJSON from 'ol/format/GeoJSON';

import {
  Fill, Stroke, Style, Text,
} from 'ol/style';

const FileSaver = require('file-saver');
const extent = [0, 0, 50, 50];
const projection = new Projection({
  code: 'xkcd-image',
  units: 'pixels',
  extent,
});
const CHARS = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');

Math.uuidFast = () => {
  const chars = CHARS;
  const uuid = new Array(36);
  let rnd = 0;
  let r;
  for (let i = 0; i < 36; i++) {
    if (i == 8 || i == 13 || i == 18 || i == 23) {
      uuid[i] = '-';
    } else if (i == 14) {
      uuid[i] = '4';
    } else {
      if (rnd <= 0x02) rnd = 0x2000000 + (Math.random() * 0x1000000) | 0;
      r = rnd & 0xf;
      rnd >>= 4;
      uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
    }
  }
  return uuid.join('');
};
export default {
  data () {
    return {
      map: {},
      list: {
        features: [],
      },
      renderlist: {
        type: 'FeatureCollection',
        crs: {
          type: 'name',
          properties: {
            name: 'EPSG:3857',
          },
        },
        features: [],
      },
      coordinates: [[]],
      roomnumber: '',
    };
  },
  created () {
  },
  mounted () {
    this.initMap();
  },
  methods: {
    renderMap () {
      const getStyles = (param) => {
        console.log(param);
        const style = new Style({
          stroke: new Stroke({
            color: '#2d9fd8',
            width: 0,
          }),
          fill: new Fill({
            color: '#2d9fd8',
            opacity: 0.8,
          }),
          text: new Text({ // 文本样式
            className: 'map-font',
            font: '14px Microsoft YaHei',
            fill: new Fill({
              color: 'black',
            }),
          }),
        });
        return style;
      };
      const styleFunction = (feature) => getStyles(feature.values_.modelId);
      const vectorSource = new VectorSource({
        features: (new GeoJSON()).readFeatures(this.renderlist),
      });
      console.log(this.map.getLayers());
      this.map.addLayer(new VectorLayer({
        source: vectorSource,
        style: styleFunction,
        title: '',
        type: 'room',
      }));
    },
    initMap () {
      this.map = new Map({
        layers: [
          new ImageLayer({
            source: new Static({
              url: '/roommap/A-10.png',
              projection,
              imageExtent: extent,
            }),
          }),
        ],
        target: 'map',
        view: new View({
          projection,
          center: getCenter(extent),
          zoom: 1,
          maxZoom: 3,
          minZoom: 1,
        }),
      });
      this.map.on('click', (evt) => {
        console.log(evt.coordinate);
        this.coordinates[0].push(evt.coordinate);
      });
    },
    confirm () {
      console.log(document.getElementById('roomnumber').value);
      const id = Math.uuidFast();
      const value = {
        type: 'Feature',
        geometry: {
          type: 'Polygon',
          coordinates: this.coordinates,
        },
        id,
        properties: { modelId: document.getElementById('roomnumber').value, id, type: 'room' },
      };
      this.renderlist.features.push(value);
      this.list.features.push(value);
      console.log(value);
      this.coordinates = [[]];
      this.roomnumber = '';
      console.log(this.list.features.length);
    },

    exportFile () {
      const content = JSON.stringify(this.list);
      const blob = new Blob([content], { type: 'text/plain;charset=utf-8' });
      FileSaver.saveAs(blob, 'A-10.roomlayer');
    },

  },
};
</script>
<style scoped>
#map {
  width: 580px;
  height: 450px;
}
.btn-style {
    width: 200px;
    font-size: 32px;
    height: 45px;
  }

.input-style {
  height: 45px;
  font-size: 30px;
  width: 400px
}
</style>
