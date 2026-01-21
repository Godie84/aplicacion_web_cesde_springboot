const API_URL = "http://localhost:8080/api";

const tablaDocentes = document.getElementById("tablaDocentes");
const formDocente = document.getElementById("formDocente");

async function cargarDocentes() {
  const res = await fetch(`${API_URL}/docentes`);
  const docentes = await res.json();

  tablaDocentes.innerHTML = "";
  docentes.forEach(d => {
    tablaDocentes.innerHTML += `
      <tr>
        <td>${d.id}</td>
        <td>${d.nombre}</td>
        <td>${d.documento}</td>
        <td>${d.correo}</td>
        <td>
          <button class="btn btn-warning btn-sm" onclick="editarDocente(${d.id})">Editar</button>
          <button class="btn btn-danger btn-sm" onclick="eliminarDocente(${d.id})">Eliminar</button>
        </td>
      </tr>
    `;
  });
}

formDocente.addEventListener("submit", async (e) => {
  e.preventDefault();

  const id = document.getElementById("docenteId").value;
  const dto = {
    nombre: document.getElementById("docenteNombre").value,
    documento: document.getElementById("docenteDocumento").value,
    correo: document.getElementById("docenteCorreo").value
  };

  if (id) {
    await fetch(`${API_URL}/docentes/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(dto)
    });
  } else {
    await fetch(`${API_URL}/docentes`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(dto)
    });
  }

  formDocente.reset();
  document.getElementById("docenteId").value = "";
  cargarDocentes();
});

async function eliminarDocente(id) {
  await fetch(`${API_URL}/docentes/${id}`, { method: "DELETE" });
  cargarDocentes();
}

async function editarDocente(id) {
  const res = await fetch(`${API_URL}/docentes/${id}`);
  const d = await res.json();

  document.getElementById("docenteId").value = d.id;
  document.getElementById("docenteNombre").value = d.nombre;
  document.getElementById("docenteDocumento").value = d.documento;
  document.getElementById("docenteCorreo").value = d.correo;
}

cargarDocentes();
