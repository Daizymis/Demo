<template>
  <div class="">
    <!-- <img src="@/assets/img/aisino-wincor.png" /> -->
    <div id="map"></div>
  </div>
</template>

<script>
import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import Select from 'ol/interaction/Select';
import { getCenter } from 'ol/extent';
import ImageLayer from 'ol/layer/Image';
import Projection from 'ol/proj/Projection';
import Static from 'ol/source/ImageStatic';
import GeoJSON from 'ol/format/GeoJSON';
import { Vector as VectorLayer } from 'ol/layer';
import { Vector as VectorSource } from 'ol/source';
import {
  Fill, Stroke, Style, Text,
} from 'ol/style';

const extent = [0, 0, 50, 50];
const projection = new Projection({
  code: 'xkcd-image',
  units: 'pixels',
  extent,
});
export default {
  data () {
    return {
      map: {},
    };
  },
  mounted () {
    this.initMap();
  },
  methods: {
    initMap () {
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
        if (param === '2311') {
          style.getStroke().setColor('#cccccc');
          style.getFill().setColor('#cccccc');
          style.getFill().setColor('#AAAAAA');
          style.getStroke().setColor('#ad8677');
        }
        style.getText().setText(param);
        console.log(style.getText());
        return style;
      };
      const styleFunction = (feature) => getStyles(feature.values_.modelId);
      const geojsonObject = {
        type: 'FeatureCollection',
        crs: {
          type: 'name',
          properties: {
            name: 'EPSG:3857',
          },
        },
        features: [{
          type: 'Feature',
          geometry: {
            type: 'Polygon',
            coordinates: [[[14.599609375, 16.9677734375], [21.6796875, 17.08984375], [21.6796875, 4.7607421875], [14.84375, 4.7607421875]]],
          },
          id: '007ABA46-7B0C-4F90-B6D4-8F6546363E',
          properties: { modelId: '2311', id: '007ABA46-7B0C-4F90-B6D4-8F6565EA2B2E', type: 'room' },

        },
        {
          type: 'Feature',
          geometry: {
            type: 'Polygon',
            coordinates: [[[21.5576171875, 17.08984375], [28.6376953125, 17.08984375], [28.515625, 5.0048828125], [21.5576171875, 5.126953125]]],
          },
          id: '007ABA46-7B0C-4F90-B6D4-8F6565EA2B2E',
          properties: { modelId: '2310', id: '007ABA46-7B0C-4F90-B6D4-8F6565EA2B2E', type: 'room' },

        },
        ],
      };
      console.log((new GeoJSON()).readFeatures(geojsonObject));
      const vectorSource = new VectorSource({
        features: (new GeoJSON()).readFeatures(geojsonObject),
      });
      const vectorLayer = new VectorLayer({
        source: vectorSource,
        style: styleFunction,
        title: '',
        type: 'room',
      });
      this.map = new Map({
        layers: [
          new ImageLayer({
            source: new Static({
              url: '/roommap/A-10.png',
              projection,
              imageExtent: extent,
            }),
          }),
          vectorLayer,
        ],
        target: 'map',
        view: new View({
          projection,
          center: getCenter(extent),
          zoom: 1,
          maxZoom: 4,
          minZoom: 1,
        }),
      });
      // 选中之后的样式
      const selectSingleClick = new Select({
        style: new Style({
          stroke: new Stroke({
            color: '#2d9fd8',
            width: 0,
          }),
          fill: new Fill({
            color: [0, 11, 214, 0.5],
            opacity: 0.5,
          }),

        }),
      });

      this.map.addInteraction(selectSingleClick);

      // 点击图层之后的处理
      selectSingleClick.on('select', (e) => {
        const features = e.target.getFeatures().getArray();


        if (features.length > 0) {
          const feature = features[0];

          const type = feature.getGeometry().getType();

          const property = feature.getProperties();

          const coordinate = getCenter(feature.getGeometry().getExtent());
          alert(property.modelId);
        }
      });
    },
  },
};
</script>
<style scoped>
#map {
  width: 1080px;
  height: 750px;
}
.ol-layer .map-font {
  font-size: 17px;
  font-weight: 400;
  line-height: 20px;
}
</style>
