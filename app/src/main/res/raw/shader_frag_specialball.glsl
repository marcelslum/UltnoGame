precision mediump float;
varying vec4 vv4_Colors;
uniform float uf_alpha;
uniform vec2 uv2_resolution;
uniform float uf_time;

highp float rand(vec2 co){
    highp float a = 12.9898;
    highp float b = 78.233;
    highp float c = 43758.5453;
    highp float dt= dot(co.xy ,vec2(a,b));
    highp float sn= mod(dt,3.14);
    return fract(sin(sn) * c);
}

void main() {

    vec2 metaball = vec2(vv4_Colors.r, vv4_Colors.g)/uv2_resolution;
    vec2 coord = vec2(gl_FragCoord.x, uv2_resolution.y - gl_FragCoord.y);
    vec2 radius = vec2(vv4_Colors.b/uv2_resolution.x, vv4_Colors.b/uv2_resolution.y);

    vec2 relativeCoord = coord/uv2_resolution;

    vec2 topLeft = vec2(metaball.x - radius.x, metaball.y - radius.y);
    vec2 bottomRight = vec2(metaball.x + radius.x, metaball.y + radius.y);

    if (coord.x > topLeft.x && coord.y > topLeft.y && coord.x < bottomRight.x && coord.y < bottomRight.y){
        gl_FragColor = vec4(0.0, 0.0, 0.0, 0.0);

        float clampValue = clamp(pow(vv4_Colors.b/2.0, 2.0) / pow(distance(coord.xy, metaball), 2.0), 0.0, 1.2);

        gl_FragColor = vec4(0.3*clampValue, 0.3*clampValue, abs(cos(uf_time))*clampValue, 1.0);

        if (clampValue > 1.2){
            float randon = rand(vec2(gl_FragCoord.x * gl_FragCoord.y, float(uf_time)));
            if (randon > 0.98){
                gl_FragColor += randon*0.2;
            }
        }

        if (gl_FragColor.x < 0.1){
            gl_FragColor.w = (0.0);
        }
    }


}