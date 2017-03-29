uniform mat4 um4_projection;
uniform mat4 um4_view;
uniform mat4 um4_model;
attribute vec2 av2_vertices;
attribute vec3 av3_uv;
varying vec2 vv2_texCoord;
varying float alpha;
void main() {
   gl_Position = um4_projection * um4_view * um4_model * vec4(av2_vertices.x, av2_vertices.y, 0.0, 0.0);
   vv2_texCoord = vec2(av3_uv.x, av3_uv.y);
   alpha = av3_uv.z;
}
