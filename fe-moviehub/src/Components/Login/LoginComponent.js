import react from "react";
import {Col, Button, Row} from "antd"

//import "./Login.scss";

const Login = ({ param }) => {
    return (
        <div className='login'>
            <Row align='middle' justify='space-around'>
                <div>Create QuizHub Account</div>
            </Row>
            <Row>
                <div className='login-body'></div>
            </Row>
            <Row>
                <Col span={16}>
                    <Button>Register</Button>
                </Col>
                <Col span={8}>
                    <Button>Login</Button>
                </Col>
            </Row>
        </div>
    );
}

export default Login;