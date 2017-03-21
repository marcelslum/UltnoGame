#version 300 es
precision mediump float;
in vec2 vv2_texCoord;
in vec4 vv4_Colors;
in float vf_alpha;
uniform sampler2D us_texture;

void main() {
    gl_FragColor = texture2D( us_texture, vv2_texCoord );
    if (gl_FragColor.a < 0.01) discard;
    gl_FragColor = vec4(gl_FragColor.r + vv4_Colors.r, gl_FragColor.g + vv4_Colors.g, gl_FragColor.b + vv4_Colors.b, gl_FragColor.a * vf_alpha * vv4_Colors.a);
}
