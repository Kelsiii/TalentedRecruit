<template>
<div>
  <div id="dialog-area">

    <message-dialog v-for="(item, index) in messages" v-bind:placement="item.placement">
      <p slot="content" v-if="item.type == 'text'" v-html="item.msg"></p>

      <position-swipe slot="content" v-if="item.type == 'positions'" v-bind:swipeData="item.swipeData">

      </position-swipe>
    </message-dialog>


  </div>

  <div id="input-area">
    <cube-input v-model="user_input" @keyup.enter.native="userInput"></cube-input>
    <a v-on:click ="startRecording"><i class="fa fa-microphone fa-lg" id="voice-input" ></i></a>

  </div>

</div>

</template>

<script>
  import MessageDialog from '@/components/Message'
  import PositionSwipe from '@/components/PositionSwipe'
  import md5 from 'js-md5';
 // import Base64 from 'js-base64'
  let Base64 = require('js-base64').Base64;
  //let test = Base64.encode("test")
  //console.log(test)

  window.URL = window.URL || window.webkitURL;
  //const socket = window.io()

  var H5Recorder = function (stream) {

    var context = new AudioContext();
    var audioInput = context.createMediaStreamSource(stream);
    var recorder = context.createScriptProcessor(4096, 1, 1);

    var audioData = {
      size: 0, //录音文件长度
      buffer: [],  //录音缓存
      inputSampleRate: context.sampleRate, //输入采样率 默认为 44.1k
      inputSampleBits: 16, //输入采样数位 8, 16
      // 百度语音识别: 原始语音的录音格式目前只支持评测 8k/16k 采样率 16bit 位深的单声道语音
      outputSampleRate: 16000, //输出采样率
      oututSampleBits: 16,  //输出采样数位 8, 16
      input: function (data) {
        this.buffer.push(new Float32Array(data));
        this.size += data.length;
      },
      compress: function () { //合并压缩
        //合并
        var data = new Float32Array(this.size);
        var offset = 0;
        for (var i = 0; i < this.buffer.length; i++) {
          data.set(this.buffer[i], offset);
          offset += this.buffer[i].length;
        }
        //压缩
        var compression = parseInt(this.inputSampleRate / this.outputSampleRate);
        var length = data.length / compression;
        var result = new Float32Array(length);
        var index = 0, j = 0;
        while (index < length) {
          result[index] = data[j];
          j += compression;
          index++;
        }
        return result;
      },
      encodeWAV: function () {
        var sampleRate = Math.min(this.inputSampleRate, this.outputSampleRate);
        var sampleBits = Math.min(this.inputSampleBits, this.oututSampleBits);
        var bytes = this.compress();
        var dataLength = bytes.length * (sampleBits / 8);
        var buffer = new ArrayBuffer(44 + dataLength);
        var data = new DataView(buffer);

        var channelCount = 1;//单声道
        var offset = 0;

        var writeString = function (str) {
          for (var i = 0; i < str.length; i++) {
            data.setUint8(offset + i, str.charCodeAt(i));
          }
        }

        // 资源交换文件标识符
        writeString('RIFF'); offset += 4;
        // 下个地址开始到文件尾总字节数,即文件大小-8
        data.setUint32(offset, 36 + dataLength, true); offset += 4;
        // WAV文件标志
        writeString('WAVE'); offset += 4;
        // 波形格式标志
        writeString('fmt '); offset += 4;
        // 过滤字节,一般为 0x10 = 16
        data.setUint32(offset, 16, true); offset += 4;
        // 格式类别 (PCM形式采样数据)
        data.setUint16(offset, 1, true); offset += 2;
        // 通道数
        data.setUint16(offset, channelCount, true); offset += 2;
        // 采样率,每秒样本数,表示每个通道的播放速度
        data.setUint32(offset, sampleRate, true); offset += 4;
        // 波形数据传输率 (每秒平均字节数) 单声道×每秒数据位数×每样本数据位/8
        data.setUint32(offset, channelCount * sampleRate * (sampleBits / 8), true); offset += 4;
        // 快数据调整数 采样一次占用字节数 单声道×每样本的数据位数/8
        data.setUint16(offset, channelCount * (sampleBits / 8), true); offset += 2;
        // 每样本数据位数
        data.setUint16(offset, sampleBits, true); offset += 2;
        // 数据标识符
        writeString('data'); offset += 4;
        // 采样数据总数,即数据总大小-44
        data.setUint32(offset, dataLength, true); offset += 4;
        // 写入采样数据
        if (sampleBits === 8) {
          for (var i = 0; i < bytes.length; i++ , offset++) {
            var s = Math.max(-1, Math.min(1, bytes[i]));
            var val = s < 0 ? s * 0x8000 : s * 0x7FFF;
            val = parseInt(255 / (65535 / (val + 32768)));
            data.setInt8(offset, val, true);
          }
        } else {
          for (var i = 0; i < bytes.length; i++ , offset += 2) {
            var s = Math.max(-1, Math.min(1, bytes[i]));
            data.setInt16(offset, s < 0 ? s * 0x8000 : s * 0x7FFF, true);
          }
        }

        return new Blob([data], { type: 'audio/wav' });
      }
    };

    //开始录音
    this.start = function () {
      audioInput.connect(recorder);
      recorder.connect(context.destination);
    }

    //停止
    this.stop = function () {
      recorder.disconnect();
    }

    //获取音频文件
    this.getBlob = function () {
      this.stop();
      return audioData.encodeWAV();
    }

    //回放
    this.play = function (audio) {
      audio.src = window.URL.createObjectURL(this.getBlob());
    }

    this.toBase64 = function(blob){
      var reader = new FileReader();
      reader.onload = function(e) {
        var wavBase64 = e.target.result;
        //console.log(wavBase64);  //此处输出为 wav格式的 base64 码
        return wavBase64
      }
      reader.readAsDataURL(blob);

      // var url = URL.createObjectURL(blob);
      // var audio = new Audio();
      // audio.src = url;
      // audio.play();
    }

    //上传
    this.upload = function (url, callback) {
      var fd = new FormData();
      fd.append("audioData", this.getBlob());
      var xhr = new XMLHttpRequest();
      if (callback) {
        xhr.upload.addEventListener("progress", function (e) {
          callback('uploading', e);
        }, false);
        xhr.addEventListener("load", function (e) {
          callback('ok', e);
        }, false);
        xhr.addEventListener("error", function (e) {
          callback('error', e);
        }, false);
        xhr.addEventListener("abort", function (e) {
          callback('cancel', e);
        }, false);
      }
      xhr.open("POST", url);
      xhr.send(fd);
    }

    //音频采集
    recorder.onaudioprocess = function (e) {
      audioData.input(e.inputBuffer.getChannelData(0));
      //record(e.inputBuffer.getChannelData(0));
    }

  };

  //获取录音机
  H5Recorder.init = function (callback) {
    navigator.mediaDevices.getUserMedia({ audio: true })
      .then(function (stream) {
        var rec = new H5Recorder(stream);
        callback(rec);
      })
      .catch(function (err) {
        console.error(err)
      })
  }

  window.H5Recorder = H5Recorder;

  let recorder  // 录音器
  let recording = false   // 是否正在录音
  let recognizing = false   // 是否正在语音识别

  //let test = md5("test")
  //console.log(test)

  export default {
    name: "Robot",
    components: {
      MessageDialog,
      PositionSwipe
    },
    data() {
      return {
        user_input: '',
        messages: []
      }
    },

    methods: {
      userInput(){
        this.messages.push({
          placement:"right",
          type: "text",
          msg: this.user_input
        })

        let company_id = this.$route.params.company_id;
        this.$ajax({
          url: '/api/search/',
          method: 'post',
          data: {
            company_id: company_id,
            user_input: this.user_input
          }
        }).then((res) =>{
          let data = res.data
          if(data.result == 1){
            if(data.type == 'text'){
              data.content.forEach(text =>{
                this.messages.push({
                  placement:"left",
                  type: "text",
                  msg: JSON.parse(text)
                })
              })
            } else {
              let positions = []
              data.content.forEach(text =>{
                positions.push(JSON.parse(text))
              })

              console.log(positions)
              this.messages.push({
                placement:"left",
                type: "positions",
                swipeData: positions
              })
            }

          } else{
            alert(data.msg)
          }

        }).catch(function (err) {
          console.log(err)
          alert('发生错误，请刷新后重试！');
        })

        this.user_input = ""



      },

      startRecording() {
        if (!recording) {
          H5Recorder.init(function (rec) {
            recorder = rec
            recorder.start()
          })
          recording = true
        } else {
          recording = false
          let buffer = recorder.getBlob()

          var reader = new FileReader();

          let emm = this;
          reader.onload = function(e) {
            var wavBase64 = e.target.result;
            //console.log(wavBase64.substring(22));  //此处输出为 wav格式的 base64 码

            let x_appid = '5ab9aac4'
            let x_param = 'eyJlbmdpbmVfdHlwZSI6ICJzbXMxNmsiLCJhdWUiOiAicmF3In0='
            let x_time = Math.floor((new Date().getTime())/1000)

            let str = x_appid + x_time + x_param
            let x_checksum = md5(str)

            emm.$ajax({
              url: '/api/search/repost',
              method: 'post',
              data: {
                audio: wavBase64.substring(22),
                'X-Param': x_param,
                'X-Appid': x_appid,
                'X-CurTime': x_time,
                'X-CheckSum': x_checksum,
                'Content-Type': 'application/x-www-form-urlencoded',
                'charset': 'utf-8',
              }
            }).then((res) =>{
              console.log(res)

            })
          }
          reader.readAsDataURL(buffer);


          //let reader = new FileReader();
          //let str = reader.readAsDataURL(buffer)
          //console.log(buffer)
          //console.log(str)


          //recorder.play()
/*

*/
          //socket.emit('chat message', buffer)
        }
      }
    },
    updated:function(){
      this.$nextTick(function(){
        var div = document.getElementById('dialog-area');
        div.scrollTop = div.scrollHeight;
      })
    }
  }
</script>

<style>
  #input-area {
    position: fixed;
    display: flex;
    bottom: 0;
    width: 90%;
    height: 50px;
  }

  #dialog-area{
    height: 500px;
    overflow: auto;
  }

  #input-area .cube-input{
    width: 90%;
    height: 40px;
  }

  #voice-input {
    position: relative;
    top: 10px;
    left: 10px;
    color: #c8c8c8;
  }


</style>
