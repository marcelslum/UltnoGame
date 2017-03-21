#version 300 es

layout(location = 0) in vec4 vertexPosition;
layout(location = 1) in vec2 vertexUV;
layout(location = 2) in vec4 vertexColors;

uniform mat4 uniform_projection;
uniform mat4 uniform_view;
uniform mat4 uniform_model;
uniform float uniform_alpha;

out vec2 vv2_texCoord;
out vec4 vv4_Colors;
out float vf_alpha;

void main() {
    gl_Position = uniform_projection * uniform_view * uniform_model * vertexPosition;
    vv2_texCoord = vertexUV;
    vv4_Colors = vertexColors;
    vf_alpha = uniform_alpha;
}
