import axios from 'axios'

const EMPLOYEE_SERVICE_BASE_URL = "http://127.0.0.1:9191/api/employees";
const EMPLOYEE_ID = 2;

class EmployeeService {
    getEmployee() {
        return axios.get(EMPLOYEE_SERVICE_BASE_URL + '/' + EMPLOYEE_ID);
    }
}

export default new EmployeeService