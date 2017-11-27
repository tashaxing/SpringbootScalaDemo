import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import {Button} from 'antd';
import axios from 'axios';

export default class ApiTest extends React.Component
{
    onGetClick()
    {
        console.log("click get");
        // get record
        axios.get("/scalatest/findid/2")
            .then(function (response) {
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    onSaveCick()
    {
        console.log("click save");
        // save one record
        axios({
                method: 'post',
                url: '/scalatest/add',
                data: {
                    id: 7,
                    name: "tashaxing",
                    age: 25
                }
            })
            .then(function (response) {
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });

    }

    onUpdateClick()
    {
        console.log("click update");
        // update one record
        axios({
            method: 'post',
            url: '/scalatest/update',
            data: {
                id: 3,
                name: "tashaxing",
                age: 25
            }
        })
            .then(function (response) {
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    onDeleteClick()
    {
        console.log("click delete");
        // save one record
        axios.post("/scalatest/delete/1")
            .then(function (response) {
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    render()
    {
        return (
            <div>
                <h1>test restapi for springboot scala</h1>
                <Button type="primary" onClick={() => this.onGetClick()}>query record</Button><br/><br/>
                <Button type="primary" onClick={() => this.onSaveCick()}>save record</Button><br/><br/>
                <Button type="primary" onClick={() => this.onUpdateClick()}>update record</Button><br/><br/>
                <Button type="primary" onClick={() => this.onDeleteClick()}>delete recorde</Button><br/><br/>
            </div> 
        );
    }
}

ReactDOM.render(<ApiTest/>, document.getElementById('apitest'));
