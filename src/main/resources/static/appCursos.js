const API_URL = "http://localhost:8080/api";

const tablaCursos = document.getElementById("tablaCursos");
const formCurso = document.getElementById("formCurso");
const selectDocente = document.getElementById("cursoDocenteId");

async function cargarDocentes() {
  const res = await fetch(`${API_URL}/docentes`);
  const docentes = await res.json();

  selectDocente.innerHTML = `<option value="">Seleccione docente</option>`;
  docentes.forEach(d => {
    selectDocente.innerHTML += `<option value="${d.id}">${d.nombre}</option>`;
  });
}

async function cargarCursos() {
  const res = await fetch(`${API_URL}/cursos`);
  const cursos = await res.json();

  tablaCursos.innerHTML = "";
  cursos.forEach(c => {
    tablaCursos.innerHTML += `
      <tr>
        <td>${c.id}</td>
        <td>${c.nombre}</td>
        <td>${c.descripcion}</td>
        <td>${c.duracionSemanas}</td>
        <td>${c.precio}</td>
        <td>${c.fechaInicio}</td>
        <td>${c.docenteNombre}</td>
        <td>
          <button class="btn btn-warning btn-sm" onclick="editarCurso(${c.id})">Editar</button>
          <button class="btn btn-danger btn-sm" onclick="eliminarCurso(${c.id})">Eliminar</button>
        </td>
      </tr>
    `;
  });
}

formCurso.addEventListener("submit", async (e) => {
  e.preventDefault();

  const id = document.getElementById("cursoId").value;

  const dto = {
    nombre: document.getElementById("cursoNombre").value,
    descripcion: document.getElementById("cursoDescripcion").value,
    duracionSemanas: parseInt(document.getElementById("cursoDuracion").value),
    precio: parseFloat(document.getElementById("cursoPrecio").value),
    fechaInicio: document.getElementById("cursoFechaInicio").value,
    docenteId: parseInt(document.getElementById("cursoDocenteId").value)
  };

  if (id) {
    await fetch(`${API_URL}/cursos/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(dto)
    });
  } else {
    await fetch(`${API_URL}/cursos`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(dto)
    });
  }

  formCurso.reset();
  document.getElementById("cursoId").value = "";
  cargarCursos();
});

async function eliminarCurso(id) {
  await fetch(`${API_URL}/cursos/${id}`, { method: "DELETE" });
  cargarCursos();
}

async function editarCurso(id) {
  const res = await fetch(`${API_URL}/cursos/${id}`);
  const c = await res.json();

  document.getElementById("cursoId").value = c.id;
  document.getElementById("cursoNombre").value = c.nombre;
  document.getElementById("cursoDescripcion").value = c.descripcion;
  document.getElementById("cursoDuracion").value = c.duracionSemanas;
  document.getElementById("cursoPrecio").value = c.precio;
  document.getElementById("cursoFechaInicio").value = c.fechaInicio;
  document.getElementById("cursoDocenteId").value = c.docenteId;
}

cargarDocentes();
cargarCursos();
