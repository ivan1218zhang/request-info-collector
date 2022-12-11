import{P as I}from"./PostItem.2751547e.js";import{r as V,s as F,i as S}from"./index.41565780.js";import{_ as E}from"./_plugin-vue_export-helper.cdc0426e.js";import{E as f,k as C,r as c,c as w,a as s,b as n,w as r,F as k,l as J,m as x,g as L,n as O,o as p,d as m,p as R,q as T}from"./index.bf3875d6.js";var v=[];V.post({url:"/request/info/collector/cache/field/field",data:{}}).then(e=>{console.log(e),e.data.urls,v=e.data.fields}).catch(e=>{console.log(e),f({showClose:!0,message:"\u8BF7\u6C42\u9519\u8BEF",type:"warning"})});const z={name:"demo",components:{JsonViewer:F,PostItem:I},setup(){const e=C(""),t=C(null);return{url:e,result:t}},computed:{},data(){return{myChartStyle:{float:"left",width:"100%",height:"90%"},field:"",value:"",chart:null,params:[],requestBody:{},response:{},activeName:"first"}},created(){},mounted(){this.initCharts()},watch:{},methods:{copyCurl(){var e="curl "+window.location.origin+"/request/info/collector/cache/field/url -H 'Content-Type:application/json' -X POST -d '"+JSON.stringify(this.requestBody)+"'";console.log(e);const t=document.createElement("input");t.setAttribute("value",e),document.body.appendChild(t),t.select(),document.execCommand("copy"),document.body.removeChild(t),f({message:"\u5DF2\u590D\u5236",type:"success"})},removeParam(e){this.params.splice(e,1)},addParam(){this.params.push({key:"",value:""})},setBlur(){document.activeElement.blur()},querySearchFields(e,t){const d=e?v.filter(this.createFilter(e)):v;let o=[];for(let a=0;a<d.length;a++)o.push({value:d[a]});console.log(o),t(o)},createFilter(e){return t=>t.value.toUpperCase().match(e.toUpperCase())},initCharts(){this.chart=S(this.$refs.chart_wrap),window.addEventListener("resize",()=>{this.chart.resize()})},setOptions(e){var t=[],d=[];const o=[],a=[];console.log(this),this.params.forEach(l=>{o.push(l.key),a.push(l.value)}),console.log(o),console.log(a),this.requestBody={fieldNames:o,fieldValues:a},V.post({url:"/request/info/collector/report/url/field",data:{fieldNames:o,fieldValues:a}}).then(l=>{this.response=l,console.log(l),t=l.data.labels,d=l.data.values,this.chart.setOption({title:{text:"URL\u5206\u5E03"},tooltip:{trigger:"axis",axisPointer:{type:"shadow"}},legend:{},color:["#d48265","#91c7ae","#749f83","#ca8622","#bda29a","#6e7074","#546570","#c4ccd3","#ff0000","#00ff00","#0000ff"],grid:{left:"3%",right:"4%",bottom:"3%",containLabel:!0},xAxis:{type:"value",boundaryGap:[0,.01]},yAxis:{type:"category",data:t},series:[{name:e,type:"bar",data:d}]})}).catch(l=>{console.log(l),f({showClose:!0,message:"\u8BF7\u6C42\u9519\u8BEF",type:"warning"})})}}},_=e=>(R("data-v-d78d73bd"),e=e(),T(),e),A={class:"post"},j={style:{display:"flex"}},G=_(()=>s("h1",null,"/request/info/collector/cache/url/field",-1)),H={class:"post-main"},M={class:"main-head"},X=_(()=>s("div",null,"\u5B57\u6BB5\u540D",-1)),D=_(()=>s("div",null,"\u5B57\u6BB5\u503C",-1)),K={class:"main-content"},Q={class:"card-header"},W=_(()=>s("span",null,"/request/info/collector/report/url/field",-1));function Y(e,t,d,o,a,l){const h=c("el-button"),B=c("el-tag"),q=c("PostItem"),N=c("JsonResult"),y=c("JsonViewer"),b=c("el-tab-pane"),U=c("el-tabs"),P=c("el-card");return p(),w(k,null,[s("div",A,[s("div",j,[G,n(h,{onClick:l.setOptions,type:"primary",style:{margin:"20px"}},{default:r(()=>[m("\u67E5\u8BE2")]),_:1},8,["onClick"])]),n(B,null,{default:r(()=>[m("\u7EDF\u8BA1\u6839\u636E\u7684\u5B57\u6BB5\uFF08\u4E00\u4E2A\u6216\u591A\u4E2A\uFF09")]),_:1}),s("div",H,[s("div",M,[X,D,n(h,{onClick:l.addParam,type:"primary"},{default:r(()=>[m("\u6DFB\u52A0")]),_:1},8,["onClick"])]),s("div",K,[(p(!0),w(k,null,J(a.params,(i,g)=>(p(),x(q,{onRemoveItem:u=>l.removeParam(g),name:i.key,"onUpdate:name":u=>i.key=u,value:i.value,"onUpdate:value":u=>i.value=u,key:g},null,8,["onRemoveItem","name","onUpdate:name","value","onUpdate:value"]))),128))])]),o.result?(p(),x(N,{key:0,result:o.result},null,8,["result"])):L("",!0)]),n(P,{class:"box-card"},{header:r(()=>[s("div",Q,[W,n(h,{class:"button",text:"",onClick:l.copyCurl},{default:r(()=>[m("\u590D\u5236\u4E3Acurl\u547D\u4EE4")]),_:1},8,["onClick"])])]),default:r(()=>[n(U,{modelValue:a.activeName,"onUpdate:modelValue":t[0]||(t[0]=i=>a.activeName=i),class:"demo-tabs",onTabClick:e.handleClick},{default:r(()=>[n(b,{label:"\u8BF7\u6C42\u4F53",name:"first"},{default:r(()=>[n(y,{value:a.requestBody,copyable:"",boxed:"",sort:""},null,8,["value"])]),_:1}),n(b,{label:"\u54CD\u5E94\u7ED3\u679C",name:"second"},{default:r(()=>[n(y,{value:a.response,copyable:"",boxed:"",sort:""},null,8,["value"])]),_:1})]),_:1},8,["modelValue","onTabClick"])]),_:1}),s("div",{ref:"chart_wrap",class:"chart_wrap",style:O(a.myChartStyle)},null,4)],64)}const ae=E(z,[["render",Y],["__scopeId","data-v-d78d73bd"]]);export{ae as default};