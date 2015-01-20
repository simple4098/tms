/*
 * File: app.js
 *
 * This file was generated by Sencha Architect version 3.0.1.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

// @require @packageOverrides
Ext.Loader.setConfig({
    enabled: true
});


Ext.application({

    requires: [
        'Ext.window.MessageBox',
        'Ext.JSON'
    ],
    models: [
        'TaskEntity',"Field"
    ],
    stores: ['TaskEntityStore','JobTypeStore'],
    views: [
        'MainView',
        'CronMakerWindow',
        'CreateTaskEntityWindow'
    ],
    controllers: [
        'Navigation'
    ],
    name: 'FQServiceApp',

    launch: function() {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'environment/init.json', false);
        xhr.send(null);

        Ext.ENV=eval("(" + xhr.responseText + ")");
        Ext.ENV.MSG={title:"系统提示"};
        startup();
       /* if(!Ext.ENV.logined){
            loginWindow = Ext.create('FQServiceApp.view.LoginWindow');
            loginWindow.show();
        }else{
            startup();
        }
*/
    }

});
var loginWindow;
var startup=function(){
    //全局设置
    if(Ext.MessageBox){
        Ext.window.MessageBox.prototype.buttonText = {
            ok: "确定",
            cancel: "取消",
            yes: "是",
            no: "否"
        };  Ext.MessageBox = Ext.Msg = new Ext.window.MessageBox();
    }
    Ext.create('FQServiceApp.view.MainView');
};
var jsonpCallback=function(response){
    Ext.MessageBox.alert('系统提示',response.message,function(){
       if(response.status){
           //loginWindow.close();*/
           startup();
       }
    });
};
