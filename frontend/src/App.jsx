import { useState } from "react";
import axios from "axios";
import "./App.css";

export default function App() {
  const [logs, setLogs] = useState([]);

  const backendUrl = "http://localhost:8080";

  const callService = async (path, serviceName) => {
    try {
      const response = await axios.post(`${backendUrl}${path}`);

      setLogs((prev) => [
        {
          serviceName,
          status: "SUCCESS",
          result: response.data,
          time: new Date().toLocaleTimeString(),
        },
        ...prev,
      ]);
    } catch (error) {
      setLogs((prev) => [
        {
          serviceName,
          status: "ERROR",
          result: "Execution failed",
          time: new Date().toLocaleTimeString(),
        },
        ...prev,
      ]);
    }
  };

  return (
    <div className="app">
      <header className="hero">
        <h1>Panel de Microservicios</h1>
        <p>
          Monitoreo de inventario, pedidos y pagos mediante un proxy de logging.
        </p>
      </header>

      <section className="actions">
        <button
          onClick={() =>
            callService("/api/services/inventory/checkStock", "Inventario")
          }
        >
          Consultar inventario
        </button>

        <button
          onClick={() =>
            callService("/api/services/orders/createOrder", "Pedidos")
          }
        >
          Crear pedido
        </button>

        <button
          onClick={() =>
            callService("/api/services/payments/processPayment", "Pagos")
          }
        >
          Procesar pago
        </button>
      </section>

      <section className="logs">
        <h2>Historial de operaciones</h2>

        {logs.length === 0 ? (
          <div className="empty">Aún no hay registros.</div>
        ) : (
          logs.map((log, index) => (
            <div className="log-card" key={index}>
              <div className="log-header">
                <span className="service-name">{log.serviceName}</span>
                <span className={log.status === "SUCCESS" ? "success" : "error"}>
                  {log.status === "SUCCESS" ? "Éxito" : "Error"}
                </span>
              </div>

              <p>
                <strong>Resultado:</strong> {log.result}
              </p>
              <p>
                <strong>Hora:</strong> {log.time}
              </p>
            </div>
          ))
        )}
      </section>
    </div>
  );
}