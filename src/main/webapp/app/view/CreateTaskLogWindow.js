/*
 * File: app/view/CreateTaskEntityWindow.js
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

Ext.define('FQServiceApp.view.CreateTaskLogWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel',
        'Ext.form.field.ComboBox',
        'Ext.button.Button',
        'Ext.grid.column.Column',
        'Ext.form.field.TextArea'
    ],
    height: 395,
    width: 1000,
    title: '日志列表',
    layout: 'fit',
    modal:true,

    initComponent: function() {
        var me = this;
        var store=Ext.data.StoreManager.lookup('TaskLogStore');
        //delete store.data;
        store.on("beforeload", function () {
            Ext.apply(this.proxy.extraParams, {
                module: me.extraParams.taskId,
                city: Ext.getCmp("cityName")==undefined?Ext.ENV.defaultCity.pinyin:Ext.getCmp("cityName").getValue(),
                startDate:Ext.getCmp("startDate")==undefined?"":Ext.getCmp("startDate").getValue()  ,
                endDate:Ext.getCmp("endDate")==undefined?"":Ext.getCmp("endDate").getValue()
            });
        });
        store.load();
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    itemId: 'contentPanel',
                    dockedItems: [
                        {
                            xtype : 'toolbar',
                            dock : 'top',
                            items : [
                               ,{
                                    xtype:'label',
                                    text:'开始时间：'
                                },{
                                    xtype:'datefield',
                                    name:"startDate"  ,
                                    id:"startDate"
                                },'-',{
                                    xtype:'label',
                                    text:'结束时间：'
                                },{
                                    xtype:'datefield',
                                    id:"endDate",
                                    name:"endDate"
                                },
                                {
                                    text:'搜索'   ,
                                    iconCls:"button_2_11_serach",
                                    handler:function(){
                                        store.loadPage(1);
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'grid',
                            dock: 'top',
                            id: 'taskLogGrid',
//                            title: '调度列表',
                            forceFit: true,
                            store:store,
                            columns: [
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'taskName',
                                    text: '任务名称'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'action',
                                    text: '请求方式'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'description',
                                    text: '详情'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'dateCreated',
                                    text: '创建时间',
                                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                        if (value !== null) {
                                            return Ext.util.Format.date(new Date(parseInt(value)),"Y-m-d H:i:s");
                                        }
                                    }
                                }
                            ]
                        },
                        {
                            xtype: 'pagingtoolbar',
                            dock: 'bottom',
                            width: 360,
                            displayInfo: true,
                            store: store
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});