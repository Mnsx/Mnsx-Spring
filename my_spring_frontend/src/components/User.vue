<template>
  <div>
    <el-container>
      <el-header>
        <el-container>
          <el-aside width="200px">
            <el-button type="success" @click="drawer = true" plain>新建</el-button>
          </el-aside>
          <el-main>
          </el-main>
        </el-container>
      </el-header>
      <el-main>
        <el-row :gutter="20">
          <el-col :span="15">
            <el-table
                :data="tableData"
                style="margin-left:50vh;width: 100%">
              <el-table-column
                  prop="id"
                  label="编号"
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="name"
                  label="姓名"
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="password"
                  label="密码"
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="balance"
                  label="余额"
                  width="180">
              </el-table-column>
              <el-table-column
                  label="操作"
                  width="250">
                <template slot-scope="scope">
                  <el-button type="info" @click="modify(scope.row.id)" plain>修改</el-button>
                  <el-button type="danger" @click="remove(scope.row.id)" plain>删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
      </el-main>
      <el-footer>
        <p style="opacity:0.5">power by Mnsx_x😁</p>
      </el-footer>
    </el-container>


    <el-drawer
        title="新建界面"
        :visible.sync="drawer"
        direction="rtl"
        :before-close="handleClose">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="金额">
          <el-input-number v-model="form.balance" :precision="0" :step="1" :max="10000"></el-input-number>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmitAdd">立即创建</el-button>
          <el-button @click="clear">清空</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>

    <el-drawer
        title="修改界面"
        :visible.sync="drawer2"
        direction="rtl"
        :before-close="handleClose">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="金额">
          <el-input-number v-model="form.balance" :precision="0" :step="1" :max="10000"></el-input-number>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmitUpdate">立即创建</el-button>
          <el-button @click="clear">清空</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      curId: 0,
      tableData: [],
      drawer: false,
      drawer2: false,
      form: {
        name: '',
        password: '',
        balance: 0,
      }
    }
  },
  methods: {
    onSubmitUpdate() {
      this.$axios.get('http://localhost:8080/users/modifyUser?id=' + this.curId + '&name=' + this.form.name + '&password=' + this.form.password + '&balance=' + this.form.balance).then((response) => {
        if (response.data.state === 200) {
          this.$message({
            message: response.data.message,
            type: "success"
          })
          this.list()
          this.drawer2 = false;
        } else {
          this.$message({
            message: "服务器出现问题，请联系管理员",
            type: "error"
          })
        }
      })
    },
    modify(id) {
      this.drawer2 = true;
      this.curId = id;
      this.$axios.get('http://localhost:8080/users/findUser?id=' + id).then((response) => {
        if (response.data.state === 200) {
          this.form.name = response.data.data.name;
          this.form.password = response.data.data.password;
          this.form.balance = response.data.data.balance;
          this.list()
        } else {
          this.$message({
            message: "服务器出现问题，请联系管理员",
            type: "error"
          })
        }
      })
    },
    remove(id) {
      this.$axios.get('http://localhost:8080/users/removeUser?id=' + id).then((response) => {
        if (response.data.state === 200) {
          this.$message({
            message: response.data.message,
            type: "success"
          })
          this.list()
        } else {
          this.$message({
            message: "服务器出现问题，请联系管理员",
            type: "error"
          })
        }
      })
    },
    onSubmitAdd() {
      this.$axios.get('http://localhost:8080/users/addUser?name=' + this.form.name + '&password=' + this.form.password + '&balance=' + this.form.balance).then((response) => {
        if (response.data.state === 200) {
          this.$message({
            message: response.data.message,
            type: "success"
          })
          this.list()
          this.drawer = false;
        } else {
          this.$message({
            message: "服务器出现问题，请联系管理员",
            type: "error"
          })
        }
      })
    },
    clear() {
      this.form.name = '';
      this.form.password = '';
      this.form.balance = 0;
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    list(){
      this.$axios.get('http://localhost:8080/users/findAll').then((response) => {
        if (response.data.state === 200) {
          this.tableData = response.data.data;
        } else {
          this.$message({
            message: "服务器出现问题，请联系管理员",
            type: error
          })
        }
      })
    }
  },
  created() {
    this.list();
  }
}
</script>

<style scoped>

</style>