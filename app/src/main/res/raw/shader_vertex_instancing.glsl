#version 300 es

uniform mat4 um4_projection;
uniform mat4 um4_view;
uniform mat4 um4_model;
uniform float uf_alpha;


out vec2 outTexCoord;
out float alpha;

layout(location = 0) in vec2 vertexPosition;
layout(location = 1) in vec3 vertexUV;



void main() {
    outTexCoord = vec2(vertexUV.x, vertexUV.y);
    alpha = vertexUV.z;
    gl_Position = um4_projection * um4_view * um4_model * vec4(vertexPosition.x, vertexPosition.y, 0.0, 1.0);
}
