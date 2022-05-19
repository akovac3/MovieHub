import axios from "axios";

export const host = process.env.REACT_APP_HOST_URL;

export const getMethod = async (url) => {
    return await (await axios.get(host + url, getAuthentication())).data;
}

export const postMethod = async (url) => {
    return await (await axios.post(host + url, getAuthentication())).data;
}

export const putMethod = async (url) => {
    return await (await axios.put(host + url, getAuthentication())).data;
}

export const deleteMethod = async (url) => {
    return await (await axios.delete(host + url, getAuthentication())).data;
}

const getAuthentication = () => {
    let config = {};
    const token = getTokenFromSession() //implement this method
    if (token) {
        config = {
            headers: { Authorization: "Bearer " + token }
        };
    }
    return config;
}